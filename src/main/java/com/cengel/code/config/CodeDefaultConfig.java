package com.cengel.code.config;

import com.cengel.code.wrapper.impl.ColumnsWrapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:06
 **/
@Configuration
@EnableConfigurationProperties({ CodeFactoryProperties.class, FreemarkerProperties.class })
public class CodeDefaultConfig {

	@Resource
	private FreemarkerProperties  freemarkerProperties;
	@Resource
	private CodeFactoryProperties codeFactoryProperties;

	@Bean
	@ConditionalOnMissingBean(ScheduledExecutorService.class)
	public ScheduledExecutorService schedule() {
		String threadName = "code-scheduled" + "-%d";
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(threadName)
				.build();
		return new ScheduledThreadPoolExecutor(50, namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setFreemarkerSettings(freemarkerProperties.getSettings());
		configurer.setPreferFileSystemAccess(freemarkerProperties.getPreferFileSystemAccess());
		configurer.setTemplateLoaderPath(freemarkerProperties.getTemplateLoaderPath());
		return configurer;
	}

	@Bean
	@ConditionalOnMissingBean(ColumnsWrapper.class)
	public ColumnsWrapper ColumnsWrapper() {
		return new ColumnsWrapper(codeFactoryProperties);
	}
}
