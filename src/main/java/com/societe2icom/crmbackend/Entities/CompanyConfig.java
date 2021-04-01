package com.societe2icom.crmbackend.Entities;


import javax.persistence.*;

@Entity
@Table(name = "company_config")
public class CompanyConfig {

@javax.persistence.Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "company",referencedColumnName = "id")
    private Company Company;
    @Column(name = "sever_url")
    private String ServerUrl;
    @Column(name = "db_name")
    private String DBName;
    @Column(name = "db_password")
    private String DBPassword;
    @Column(name = "company_type")
    private String CompanyType;
    @Column(name = "db_driver")
    private String DBDriver;
    @Column(name = "db_type")
    private String DBType;
    @Column(name = "server_port")
    private String ServerPort;
    @Column(name = "db_port")
    private String DBPort;

    @Column(name="db_username")
    private String DBUsername;

    @Column(name = "is_woocommerce")
    private boolean IsWooCommerce;

    public CompanyConfig() {
    }

    public CompanyConfig(Long id) {
        Id = id;
    }

    public CompanyConfig(Long id, com.societe2icom.crmbackend.Entities.Company company, String serverUrl, String DBName, String DBPassword, String companyType, String DBDriver, String DBType, String serverPort, String DBPort, String DBUsername, boolean isWooCommerce) {
        Id = id;
        Company = company;
        ServerUrl = serverUrl;
        this.DBName = DBName;
        this.DBPassword = DBPassword;
        CompanyType = companyType;
        this.DBDriver = DBDriver;
        this.DBType = DBType;
        ServerPort = serverPort;
        this.DBPort = DBPort;
        this.DBUsername = DBUsername;
        IsWooCommerce = isWooCommerce;
    }

    public String getDBUsername() {
        return DBUsername;
    }

    public void setDBUsername(String DBUsername) {
        this.DBUsername = DBUsername;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public com.societe2icom.crmbackend.Entities.Company getCompany() {
        return Company;
    }

    public void setCompany(com.societe2icom.crmbackend.Entities.Company company) {
        Company = company;
    }

    public String getServerUrl() {
        return ServerUrl;
    }

    public void setServerUrl(String serverUrl) {
        ServerUrl = serverUrl;
    }

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getDBPassword() {
        return DBPassword;
    }

    public void setDBPassword(String DBPassword) {
        this.DBPassword = DBPassword;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String companyType) {
        CompanyType = companyType;
    }

    public String getDBDriver() {
        return DBDriver;
    }

    public void setDBDriver(String DBDriver) {
        this.DBDriver = DBDriver;
    }

    public String getDBType() {
        return DBType;
    }

    public void setDBType(String DBType) {
        this.DBType = DBType;
    }

    public String getServerPort() {
        return ServerPort;
    }

    public void setServerPort(String serverPort) {
        ServerPort = serverPort;
    }

    public String getDBPort() {
        return DBPort;
    }

    public void setDBPort(String DBPort) {
        this.DBPort = DBPort;
    }

    public boolean isWooCommerce() {
        return IsWooCommerce;
    }

    public void setWooCommerce(boolean wooCommerce) {
        IsWooCommerce = wooCommerce;
    }

    @Override
    public String toString() {
        return "CompanyConfig{" +
                "Id=" + Id +
                ", Company=" + Company +
                ", ServerUrl='" + ServerUrl + '\'' +
                ", DBName='" + DBName + '\'' +
                ", DBPassword='" + DBPassword + '\'' +
                ", CompanyType='" + CompanyType + '\'' +
                ", DBDriver='" + DBDriver + '\'' +
                ", DBType='" + DBType + '\'' +
                ", ServerPort='" + ServerPort + '\'' +
                ", DBPort='" + DBPort + '\'' +
                ", DBUsername='" + DBUsername + '\'' +
                ", IsWooCommerce=" + IsWooCommerce +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
