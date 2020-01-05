package application.menu;

public class MenuElement {

    private String url;
    private String cssClass;
    private String hrefCssClass;
    private String name;
    private String description;

    public MenuElement(String url, String hrefCssClass, String cssClass, String name, String description) {
        this.url = url;
        this.cssClass = cssClass;
        this.name = name;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getHrefCssClass() {
        return hrefCssClass;
    }

    public void setHrefCssClass(String hrefCssClass) {
        this.hrefCssClass = hrefCssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
