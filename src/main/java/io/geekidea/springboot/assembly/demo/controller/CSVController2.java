package io.geekidea.springboot.assembly.demo.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.geekidea.springboot.assembly.demo.dao.ManageApplicationSceneMapper;
import io.geekidea.springboot.assembly.demo.entity.ManageApplicationScene;
import io.geekidea.springboot.assembly.demo.entity.ManageApplicationSceneTree;
import io.geekidea.springboot.assembly.demo.model.RestResponse;
import io.geekidea.springboot.assembly.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/scene/")
@Slf4j

public class CSVController2 {

    @Autowired
    private ManageApplicationSceneMapper mapper;

    @GetMapping("/init")
    public RestResponse init() throws Exception {

        String fileName = "/Users/zhangqi1092/Documents/test/application_scene_mapping.csv";


        //
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        try (FileReader fileReader = new FileReader(fileName); CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {
            //key:京东零售 value:id
            Map<String, Long> level1Map = new HashMap<>();
            Map<String, Long> level2Map = new HashMap<>();
            Map<String, Long> level3Map = new HashMap<>();

            // 遍历CSV文件
            csvParser.getRecords().forEach(csvRecord -> {
                // 获取字段值
                String level1 = csvRecord.get(0);
                String level2 = csvRecord.get(1);
                String level3 = csvRecord.get(2);
                String level4 = csvRecord.get(3);

                // 一、一级首次插入
                Long level1Id = level1Map.get(level1);
                if (level1Id == null) {
                    level1Id = insertScene(null, level1, 1);// 返回的是下一级的pid
                    level1Map.put(level1, level1Id);
                    // 2、插入二级
                    Long level2Id = insertScene(level1Id, level2, 2);
                    level2Map.put(level2, level2Id);
                    // 3、插入三级
                    Long level3Id = insertScene(level2Id, level3, 3);
                    level3Map.put(level3, level3Id);
                    // 4、插入四级 无须返回pid
                    Long level4Id = insertScene(level3Id, level4, 4);

                    handRelation(level4Id, level1Map, level2Map, level3Map);

                } else {
                    //二、一级非首次插入，主要是判断需不需要插入二级
                    Long level2Id = level2Map.get(level2);
                    
                    if (level2Id == null) {
                        // 2、插入二级
                        level2Id = insertScene(level1Id, level2, 2);
                        level2Map.put(level2, level2Id);
                        // 3、插入三级
                        Long level3Id = insertScene(level2Id, level3, 3);
                        level3Map.put(level3, level3Id);
                        // 4、插入四级
                        Long level4Id = insertScene(level3Id, level4, 4);

                        handRelation(level4Id, level1Map, level2Map, level3Map);
                    } else {
                        //三、二级非首次插入，主要是判断需不需要插入三级
                        Long level3Id = level3Map.get(level3);
                        if (level3Id == null) {
                            // 3、插入三级
                            level3Id = insertScene(level2Id, level3, 3);
                            level3Map.put(level3, level3Id);
                            // 4、插入四级
                            Long level4Id = insertScene(level3Id, level4, 4);

                            handRelation(level4Id, level1Map, level2Map, level3Map);
                        } else {
                            //四、三级非首次插入，直接插入四级
                            Long level4Id = insertScene(level3Id, level4, 4);

                            handRelation(level4Id, level1Map, level2Map, level3Map);
                        }
                    }
                }

            });
            return RestResponse.ok();
        } catch (Exception e) {
            log.error("init exception >>>", e);
            return RestResponse.failure(e.getMessage());
        }


    }


    @GetMapping("/getIds")
    public RestResponse getIds() throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> resMap1 = new HashMap<>();
        Map<String, Object> resMap2 = new HashMap<>();
        String fileName = "/Users/zhangqi1092/Documents/test/application_scene_mapping.csv";
        int line=0;
        //
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        try (FileReader fileReader = new FileReader(fileName); CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {

            // 遍历CSV文件
            for (CSVRecord record : csvParser.getRecords()) {
                line++; // 行号

                // 获取字段值
                List<String> names = new ArrayList<>(record.size());
                for (String value : record) {
                    names.add(value);
                }
                log.error("getIds names 参数 inline:{} names:{}  ", line, names);
                List<Long> ids = new ArrayList<>();
                Long pid = null;

                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    int level = i + 1;
                    log.info("getIds inline:{} name:{} level:{} pid:{}", line, name, level, pid);
                    Long id = mapper.selectIdByNameAndLevelAndPid(name, level, pid, DigestUtils.md5Hex(name));


                    if (id != null) {
                        pid = id;
                        ids.add(id);
                    } else {
                        System.out.println("No record found for: " + name);
                        continue;
                    }
                }
                resMap1.put(line+"", ids);
                resMap2.put(line+"", ids.get(3));
            }
            resMap.put("data1", resMap1);
            resMap.put("data2", resMap2);
            return RestResponse.ok(resMap);
        } catch (Exception e) {
            log.error("getIds exception >>>", e);
            return RestResponse.failure(e.getMessage());
        }


    }

    private void handRelation(Long level4Id, Map<String, Long> level1Map, Map<String, Long> level2Map, Map<String, Long> level3Map) {
        // 此时从mysql中查询最后一次的关系
        List<ManageApplicationScene> sceneList = new ArrayList<>();
        ManageApplicationScene current = mapper.selectById(level4Id);

        while (current != null && current.getLevel() > 0) {
            sceneList.add(current);
            current = mapper.selectById(current.getPid());
        }

        // 根据level从小到大排序
        sceneList = sceneList.stream().sorted(Comparator.comparing(ManageApplicationScene::getLevel)).collect(Collectors.toList());

        level1Map.clear();
        level2Map.clear();
        level3Map.clear();

        level1Map.put(sceneList.get(0).getNameCn(), sceneList.get(0).getId());
        level2Map.put(sceneList.get(1).getNameCn(), sceneList.get(1).getId());
        level3Map.put(sceneList.get(2).getNameCn(), sceneList.get(2).getId());
    }


    @GetMapping("/listTree")
    public RestResponse listTree() throws Exception {


        try {
            List<ManageApplicationScene> list = mapper.select();
            // 将返回结果进行树形处理
            List<ManageApplicationSceneTree> res = buildTree(list);
            return RestResponse.ok(res);
        } catch (Exception e) {
            log.error("init exception >>>", e);
            return RestResponse.failure(e.getMessage());
        }


    }

    public List<ManageApplicationSceneTree> buildTree(List<ManageApplicationScene> list) {
        Map<Long, ManageApplicationSceneTree> nodeMap = new HashMap<>();
        List<ManageApplicationSceneTree> roots = new ArrayList<>();

        // 遍历并创建节点
        for (ManageApplicationScene scene : list) {
            ManageApplicationSceneTree treeNode = new ManageApplicationSceneTree();
            BeanUtils.copyProperties(scene, treeNode);
            nodeMap.put(treeNode.getId(), treeNode);

            // 根据 parentId 判断是否为根节点
            if (treeNode.getPid() == null || treeNode.getPid().equals(0L)) {
                roots.add(treeNode);
            }
        }

        // 构建父子关系
        for (ManageApplicationSceneTree treeNode : nodeMap.values()) {
            Long parentId = treeNode.getPid();
            if (parentId != null && !parentId.equals(0L)) {
                ManageApplicationSceneTree parent = nodeMap.get(parentId);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(treeNode);
                } else {
                    System.err.println("Parent node not found for node with id: " + treeNode.getId());
                }
            }
        }

        return roots;
    }

    private Long insertScene(Long pid, String name, int level) {
        ManageApplicationScene scene = new ManageApplicationScene();
        scene.setPid(pid);
        scene.setNameCn(name);
        scene.setNameEn(null);
        scene.setLevel(level);
        scene.setIsLeaf(level == 4 ? 1 : 0);
        scene.setDescription("desc");
        scene.setCreator("xx");
        scene.setModifier("xx");
        scene.setCreateTime(new java.util.Date());
        scene.setModifyTime(new java.util.Date());
        mapper.insert(scene);
        return scene.getId();
    }
}


