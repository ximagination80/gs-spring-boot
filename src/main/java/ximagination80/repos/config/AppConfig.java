package ximagination80.repos.config;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AppConfig extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String key;

    private String value;
    private String comment;

    public AppConfig() {
        this(null, null, null);
    }

    public AppConfig(String key, String value, String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
