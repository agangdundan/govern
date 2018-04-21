package xyz.frt.govern.model;

public class DailyPatrol extends BaseEntity {

    private String reportPerson;

    private Integer reportPersonId;

    private String title;

    private Integer patrolType;

    private Integer classify;

    private String place;

    private String addrGnote;

    private String content;

    private String pics;

    private String inspector;

    private Integer inspectTime;

    private String result;

    private Integer status;

    private Integer type;

    private Integer level;

    private Integer completeTime;

    public String getReportPerson() {
        return reportPerson;
    }

    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson == null ? null : reportPerson.trim();
    }

    public Integer getReprotPersonId() {
        return reportPersonId;
    }

    public void setReprotPersonId(Integer reportPersonId) {
        this.reportPersonId = reportPersonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPatrolType() {
        return patrolType;
    }

    public void setPatrolType(Integer patrolType) {
        this.patrolType = patrolType;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getAddrGnote() {
        return addrGnote;
    }

    public void setAddrGnote(String addrGnote) {
        this.addrGnote = addrGnote == null ? null : addrGnote.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Integer getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Integer inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }
}