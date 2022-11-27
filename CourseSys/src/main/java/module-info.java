module com.example.coursesys {
    requires javafx.controls;
    requires javafx.fxml;
    //requires mysql.connector.java;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;


    opens com.example.sportoAiksteliuRezervacija to javafx.fxml;
    exports com.example.sportoAiksteliuRezervacija;

    opens com.example.sportoAiksteliuRezervacija.ds to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.sportoAiksteliuRezervacija.ds;
    exports com.example.sportoAiksteliuRezervacija.ds.enums;
    opens com.example.sportoAiksteliuRezervacija.ds.enums to java.persistence, javafx.fxml, org.hibernate.orm.core;
    opens com.example.sportoAiksteliuRezervacija.fxControllers to javafx.fxml;
    exports com.example.sportoAiksteliuRezervacija.fxControllers;
}
