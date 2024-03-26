package kr.basic.security.config.oauth.provider;

import java.util.Map;
import java.util.Objects;

public class googleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    public googleUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }
    @Override
    public String getProviderId() {
        return (String)attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
