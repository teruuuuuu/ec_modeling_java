package jp.co.teruuu.ec_modeling;

import jp.co.teruuu.ec_modeling.init_data.InitMain;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.teruuu.ec_modeling.infla.data.dao")
public class BootMain {
  private final static Logger logger = LoggerFactory.getLogger(BootMain.class);

  public static void main(String[] args) {
    SpringApplication.run(BootMain.class, args);
//    InitMain.insert();
  }
}
