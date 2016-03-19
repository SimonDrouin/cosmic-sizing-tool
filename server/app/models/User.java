package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {

    @Constraints.Required
    public String name;
    
    @Constraints.Required
    public String password;
    
    @Constraints.Required
    @Column(unique = true)
    public String alias;
    
    public boolean deleted;
    public boolean disponible;
    
    @Constraints.Email
    @Constraints.Required
    @Column(unique = true)
    public String email;
    
    @Formats.DateTime(pattern="yyyy/MM/dd")
    Date created_at;
    
    @Id
    @Constraints.Min(10)
    public Long id;
    
    public static List<Project> projects;
    
    @Constraints.Required
    public String url;
    
    @Constraints.Required
    public String company;
    
    @Constraints.Required
    public String location; 
    
    public User(){
        this.created_at = new Date();
        this.deleted = false;
        this.disponible = false;
    }
    public User(Long id, String name, String pwd, String email) {
        this.id = id;
        this.created_at = new Date();
        this.name = name;
        this.password = pwd;
        this.email = email;
        this.deleted = false;
        this.disponible = false;
    }
    // Emails secondaires
    public List<String> emails;
    

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public List<Organisation> organisations = new ArrayList<Organisation>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Certification> certifications = new ArrayList<Certification>();
    
    public void addEmail(String email) {
        if(emails == null){
            this.emails = new ArrayList<String>();
        }
        this.emails.add(email);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

}
