package com.weile.utils;

import com.weile.client.PROMPTENUM;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: xwl
 * @Date: 2024/5/22 14:44
 * @Description: 匹配长尾词title
 **/
@Component
public class LongTailWordUtils {
    @Resource
    private KeyWordsRepository keyWordsRepository;

    /**
     * 关键词生成8个
     * @param title
     * @return
     */
    public String generateKeyWords(String title){
        List<KeyWords> keyWords = keyWordsRepository.findTop8ByKeyNameContainingOrderByUseCountAsc(title);
        if (!keyWords.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for (KeyWords keyWord : keyWords) {
                keyWord.setUseCount(keyWord.getUseCount()+1);
                stringBuilder.append(title).append(keyWord.getKeyName()).append(",");
            }
            //use完毕更新次数
            keyWordsRepository.saveAll(keyWords);
            return stringBuilder.toString();
        }else {
            return title;
        }
    }

    /**
     * 标题的上长尾词只生成4个
     * @param title
     * @return
     */

    public String generateTitle(String title) {
        List<KeyWords> keyWords = keyWordsRepository.findTop8ByKeyNameContainingOrderByUseCountAsc(title);
        if (!keyWords.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                stringBuilder.append(title).append("-").append(keyWords.get(i).getKeyName()).append("_");
            }
            return stringBuilder.toString();
        }
        return title;
    }
}
