package Model;

/**
 * Created by T.A on 13/05/2015.
 */
public class newsitem  {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String title;
    public String desc;
    public String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public newsitem(String title,String desc,String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;

    }
}
