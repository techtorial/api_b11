package pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlackMessagePojo {
    private String channel;
    private String text;
}
