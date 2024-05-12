/**
  * Copyright 2024 json.cn 
  */
package com.weile.client.Response;
import java.util.List;

/**
 * Auto-generated: 2024-05-12 18:37:4
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/
 */
public class GptResp {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choices> choices;
    private String system_fingerprint;
    private Usage usage;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setObject(String object) {
         this.object = object;
     }
     public String getObject() {
         return object;
     }

    public void setCreated(long created) {
         this.created = created;
     }
     public long getCreated() {
         return created;
     }

    public void setModel(String model) {
         this.model = model;
     }
     public String getModel() {
         return model;
     }

    public void setChoices(List<Choices> choices) {
         this.choices = choices;
     }
     public List<Choices> getChoices() {
         return choices;
     }


    public void setSystem_fingerprint(String system_fingerprint) {
         this.system_fingerprint = system_fingerprint;
     }
     public String getSystem_fingerprint() {
         return system_fingerprint;
     }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}