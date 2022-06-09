//package com.skills.skills.models;
//
//
//import com.skills.skills.data.SkillsCategoryRepository;
//import com.skills.skills.data.TagRepository;
//import com.skills.skills.models.skill.SkillsCategory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LoadDatabase {
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
////    CommandLineRunner initDatabase(SkillsCategoryRepository repository) {
//        CommandLineRunner initDatabase(TagRepository repository) {
//
//        return args -> {
//
//            log.info("Preloading " + repository.save(new Tag("Skills I want Share")));
//            log.info("Preloading " + repository.save(new Tag("Skills I want To Learn")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Charity/Non-Profit")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Foreign Language")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Dance")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Leisure Sport")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Wood Working")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Coding")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Paint")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Music")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Crafting")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Ceramics")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Charity/Non-Profit")));
//            log.info("Preloading " + repository.save(new SkillsCategory("Ceramics")));

//        };
//    }
//}