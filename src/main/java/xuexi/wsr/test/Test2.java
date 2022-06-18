package xuexi.wsr.test;

import org.junit.Test;
import xuexi.wsr.AutoWired;
import xuexi.wsr.controller.UserController;

import java.util.stream.Stream;

public class Test2 {

    @Test
    public void test() {
        UserController userController=new UserController();
        Class<? extends UserController> clazz = userController.getClass();

        //获取所有的属性值
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            AutoWired annotation = field.getAnnotation(AutoWired.class);
            if (annotation!=null){
                field.setAccessible(true);
                //获取属性的类型
                Class<?> type = field.getType();
                try {
                    Object o=type.newInstance();
                    field.set(userController,o);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }
}
