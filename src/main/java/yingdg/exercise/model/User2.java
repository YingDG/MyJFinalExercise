package yingdg.exercise.model;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 级联查询示例
 * <p>
 * Created by YingDG on 2017/3/22.
 */
public class User2 extends Model<User2> {
    public static final User2 dao = new User2();

    static {
        Prop p = PropKit.use("jdbc.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbc.url"), p.get("username"), p.get("password"));
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);

        // 添加映射
        activeRecordPlugin.addMapping("User", User.class);
        activeRecordPlugin.addMapping("User2", User2.class);

        // 与web环境需要手动调用一次相关插件的start()方法
        c3p0Plugin.start();
        activeRecordPlugin.start();
    }

    // 级联查询
    public static void main(String[] args) {
        String sql = "select u1.*, u2.sex from user u1 join user2 u2 on u1.id = u2.id where u1.id = ?";
        User2 user2 = User2.dao.findFirst(sql, 2);
        System.out.println(user2.get("sex"));
    }

}
