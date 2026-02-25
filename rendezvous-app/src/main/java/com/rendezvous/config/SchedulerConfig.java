package com.rendezvous.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

//classe apenas de configuração para ativar o motor de tarefas automaticas
@Configuration
@EnableScheduling
public class SchedulerConfig {
}
