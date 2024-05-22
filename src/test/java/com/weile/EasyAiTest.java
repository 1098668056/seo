package com.weile;

import com.weile.repository.KeyWordsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wlld.naturalLanguage.Talk;
import org.wlld.naturalLanguage.TemplateReader;
import org.wlld.naturalLanguage.Tokenizer;
import org.wlld.naturalLanguage.WordTemple;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xwl
 * @Date: 2024/5/22 13:20
 * @Description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class EasyAiTest {
    @Resource
    private KeyWordsRepository keyWordsRepository;

    @Test
    public void Nlp() throws Exception {
        //通过txt默认格式进行读取
        TemplateReader templateReader = new TemplateReader();
        WordTemple wordTemple = new WordTemple();//初始化语言模版，该语言模板训练结束后一定要static出来,在内存中长期持有，Talk识别构造参数进行复用
        //wordTemple.setTreeNub(9);
        //wordTemple.setTrustPunishment(0.5);
        //读取语言模版，第一个参数是模版地址，第二个参数是编码方式 (教程里的第三个参数已经省略)
        //同时也是学习过程
        templateReader.read("/Users/data/Desktop/myDocument/model.txt", "UTF-8", wordTemple);

        Talk talk = new Talk(wordTemple);
        //输入语句进行识别，若有标点符号会形成LIST中的每个元素
        //返回的集合中每个值代表了输入语句，每个标点符号前语句的分类
        List<Integer> list = talk.talk("空调坏了，帮我修一修");
        System.out.println(list);
        /////////////////////////////////自定义输入训练语句
        WordTemple wordTempleNew = new WordTemple();//初始化语言模版，该语言模板训练结束后一定要static出来,在内存中长期持有，Talk识别构造参数进行复用
        Tokenizer tokenizer = new Tokenizer(wordTempleNew);//学习类
        //训练模板 主键为类别id,值为该类别id的语句集合
        //注意
        //1，若训练量不足，建议训练语句通过标点符号拆分为若干句，且不要将标点符号带入训练语句
        //2，包含数字的语句用统一的占位符代替 例如 35,3,36% 变为 #,#,#%
        Map<Integer, List<String>> model = new HashMap<>();
        //开始训练
        tokenizer.start(model);
        ///////////////////////////////////单纯对输入语句进行切词结果返回，不进行识别
        wordTemple.setSplitWord(true);//将模板设置成纯切词模式
        List<List<String>> lists = talk.getSplitWord("空调坏了，帮我修一修");
        for (List<String> list2 : lists) {
            System.out.println(list2);
        }
    }

}
