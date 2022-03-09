package by.elmax19.commonsstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
public class PropertyConfig {
    /**
     * Database prefix of tables to work with
     */
    private String workspacePrefix;

    public String getWorkspacePrefix() {
        return workspacePrefix;
    }

    public void setWorkspacePrefix(String workspacePrefix) {
        this.workspacePrefix = workspacePrefix;
    }
}
