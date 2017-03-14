package Model;

/**
 * Created by T.A on 14/05/2015.
 */
public class galleryItem {

    public String title;
    public String image;

    public galleryItem(String title,String image) {
        this.title = title;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
