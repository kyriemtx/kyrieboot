package com.mtx.kyrieboot.system.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.log.entity.SysLog;
import com.mtx.kyrieboot.system.log.mapper.SysLogMapper;
import com.mtx.kyrieboot.system.log.service.SysLogService;
import com.mtx.kyrieboot.utils.IpInfoUtils;
import com.mtx.kyrieboot.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName SysLogServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:42
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int saveLoginLog(HttpServletRequest request, String message, String name) {
        try {
            String idAddr = IpInfoUtils.getIpAddr(request);
            String ipSource = IpInfoUtils.getipSource(idAddr);
            String browser = IpInfoUtils.getBrowser(request);
            String systemName = IpInfoUtils.getSystemName(request);
            SysLog sysLog = SysLog.builder()
                    .username(name)
                    .browserName(browser)
                    .createDate(new Date())
                    .id(UUIDUtils.getUUID())
                    .ipAddress(idAddr)
                    .ipSource(ipSource)
                    .message(message)
                    .systemName(systemName)
                    .build();
            return sysLogMapper.insert(sysLog);
        }catch (Exception e){
            log.error("获取ip来源出错");
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public IPage<SysLog> findSysLogPage(Page page) {
        return sysLogMapper.findSysLogPage(page);
    }
}
