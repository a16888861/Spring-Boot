package com.kali.blog.common.CommonScheduling;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kali.blog.common.aspect.SysLogEntity;
import com.kali.blog.common.aspect.SysLogMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日志相关定时任务
 *
 * @author Elliot
 * @date 2021/10/17 9:12 下午
 */
@Log4j2
@Component
@EnableScheduling
public class LogScheduling {

    @Resource
    private SysLogMapper logMapper;

    /**
     * 每天凌晨2:00删除15天前的日志数据
     */
    @Scheduled(cron = "0 0 2 1/1 * ? ")
    public void deleteLogEveryDay() {
        log.info("开始删除15天前的日志数据～");
        logMapper.delete(new LambdaQueryWrapper<SysLogEntity>().le(SysLogEntity::getCreateDate, LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        log.info("15天前的日志数据清除完成～");
    }
}
