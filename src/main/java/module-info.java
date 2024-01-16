module com.janjac {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires java.sql;
    requires jbcrypt;
    requires javafaker;
    exports com.janjac.controllers.auth;
    exports com.janjac.controllers.components;
    exports com.janjac.controllers.bus;
    exports com.janjac.models;
    exports com.janjac.fx;
    opens com.janjac to javafx.fxml;
    exports com.janjac;
}
