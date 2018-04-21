package xyz.frt.govern.model;

public class ReportInfo extends BaseEntity {

    private String reportPerson;

    private Integer reportPersonId;

    private Integer viewType;

    private Integer classify;

    private String addrType;

    private String addrGnote;

    private String content;

    private String pics;

    private String handler;

    private Integer handlerId;

    private Integer handleTime;

    private String result;

    private Integer infoStatus;

    private String creator;

    public String getReportPerson() {
        return reportPerson;
    }

    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson == null ? null : reportPerson.trim();
    }

    public Integer getReportPersonId() {
        return reportPersonId;
    }

    public void setReportPersonId(Integer reportPersonId) {
        this.reportPersonId = reportPersonId;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType == null ? null : addrType.trim();
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public Integer getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Integer handleTime) {
        this.handleTime = handleTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}