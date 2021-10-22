package com.kali.blog.common.CommonScheduling;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kali.blog.common.aspect.SysLogEntity;
import com.kali.blog.common.aspect.SysLogMapper;
import com.kali.blog.common.constant.CommonConstants;
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
//    @Scheduled(cron = "0/60 * * * * ? ")
    public void deleteLogEveryDay() {
        int count = logMapper.selectCount(new LambdaQueryWrapper<SysLogEntity>().le(SysLogEntity::getCreateDate, LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).intValue();
        log.info("15天前的数据共有{}条", count);
        if (count > 0) {
            log.info("开始删除{}及其之前的日志数据 - 执行时间{{}}", LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")), LocalDateTime.now().format(CommonConstants.DATE_TIME_FORMATTER));
            logMapper.delete(new LambdaQueryWrapper<SysLogEntity>().le(SysLogEntity::getCreateDate, LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            log.info("日志数据清除完成～");
        } else {
            log.info("日志数据无需清理 - 执行时间{{}}", LocalDateTime.now().format(CommonConstants.DATE_TIME_FORMATTER));
        }
    }
}
