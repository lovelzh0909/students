import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        List<Stu> list = new ArrayList();//全局变量，保持每次操控的是同一个集合

        while (true) {
            //界面搭建
            System.out.println("********学生信息管理系统********");
            System.out.println("1->查询学生");
            System.out.println("2->增加学生");
            System.out.println("3->修改学生");
            System.out.println("4->删除学生");
            System.out.println("0->退出系统");

            String key  = new Scanner(System.in).nextLine();// 接收用户输入的key，使用switch case匹配

            switch (key) {
                case "1":
                    findAll(list);//查询业务
                    break;
                case "2":
                    System.out.println("请输入增加的学生编号");
                    String id = new Scanner(System.in).nextLine();
                    System.out.println("请输入增加的学生姓名");
                    String name = new Scanner(System.in).nextLine();
                    add(list, id, name);//增加业务
                    break;
                case "3":
                    System.out.println("请输入要修改的学生编号");
                    String editID = new Scanner(System.in).nextLine();
                    System.out.println("请输入要修改的学生姓名");
                    String editName = new Scanner(System.in).nextLine();
                    edit(list, editID, editName);//编辑业务
                    break;
                case "4":
                    System.out.println("请输入要删除的学生编号");
                    String delID = new Scanner(System.in).nextLine();
                    delete(list, delID);//删除业务
                    break;
                default://这里简写了，除了1234都是退出
                    System.out.println("退出系统");
                    System.exit(0);//退出处理
                    break;
            }
        }

    }

    public static void findAll(List<Stu> list) {
        //查询前判断集合是否为空

        if (list.size() == 0) {
            System.out.println("暂无学生信息，请返回重试");
            return;
        } else {
            for (Stu stu : list) {
                System.out.println(stu.getName());//这里只简单显示学生姓名
            }
        }

    }

    public static void add(List<Stu> list, String id, String name) {

        //添加前判断学号是否存在

        for (Stu stu : list) {
            if (stu.getId().equals(id)) {
                System.out.println("学号冲突，请重试");
                return;
            }
        }

        Stu s = new Stu(id, name);
        list.add(s);
        System.out.println("添加成功");
    }

    public static void edit(List<Stu> list, String editID, String editName) {

        //编辑前判断学号是否存在

        for (Stu stu : list) {
            if (!stu.getId().equals(editID)) {
                System.out.println("该学生不存在，请重试");
                return;
            } else {

                stu.setName(editName);
                list.set(Integer.parseInt(editID) - 1, stu);//注意-1，否则索引会越界
                System.out.println("修改成功");
            }

        }

    }

    public static void delete(List<Stu> list, String delID) {

        //删除前判断学号是否存在
        for (Stu stu : list) {
            if (!stu.getId().equals(delID)) {
                System.out.println("该学生不存在，请重试");
                return;
            } else {

                list.remove(Integer.parseInt(delID) - 1);
                System.out.println("删除成功");
                break;
                // 遍历时这个break不可以省略，集合中删除元素已经改变了整体索引序列 ->java.util.ConcurrentModificationException
            }

        }

    }

}
