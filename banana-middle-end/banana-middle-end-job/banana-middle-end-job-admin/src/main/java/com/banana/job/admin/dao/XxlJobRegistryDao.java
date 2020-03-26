package com.banana.job.admin.dao;

import com.banana.job.entity.po.XxlJobRegistry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 金鹏祥 on 16/9/30.
 */
@Mapper
public interface XxlJobRegistryDao {

    public List<Integer> findDead(@Param("timeout") int timeout);

    public int removeDead(@Param("ids") List<Integer> ids);

    public List<XxlJobRegistry> findAll(@Param("timeout") int timeout);

    public int registryUpdate(@Param("registryGroup") String registryGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue);

    public int registrySave(@Param("registryGroup") String registryGroup,
                            @Param("registryKey") String registryKey,
                            @Param("registryValue") String registryValue);

    public int registryDelete(@Param("registryGroup") String registGroup,
                          @Param("registryKey") String registryKey,
                          @Param("registryValue") String registryValue);

}
