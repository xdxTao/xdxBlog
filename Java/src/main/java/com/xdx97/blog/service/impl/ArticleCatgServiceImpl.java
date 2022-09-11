package com.xdx97.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.ArticleCatg;
import com.xdx97.blog.bean.query.ArticleCatgQuery;
import com.xdx97.blog.bean.vo.ArticleCatgVO;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.enums.StatusEnum;
import com.xdx97.blog.mapper.ArticleCatgMapper;
import com.xdx97.blog.service.ArticleCatgService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 文章分类
 *
 * @author 小道仙97
 * @date 22/06/22
 */
@Service
public class ArticleCatgServiceImpl extends ServiceImpl<ArticleCatgMapper, ArticleCatg> implements ArticleCatgService {

    @Override
    public ResultObj frontList(ArticleCatgQuery articleCatgQuery) {
        articleCatgQuery.setStatus(StatusEnum.ENABLE);
        final ArticleCatgQuery query = ObjectUtil.clone(articleCatgQuery);
        return ResultObj.success(getChild(baseMapper.listByParentId(0, query), articleCatgQuery));
    }

    @Override
    public ResultObj add(ArticleCatg articleCatg, InformationVO informationVO) {
        articleCatg.setCreateAt(LocalDateTime.now())
                .setCreateBy(informationVO.getId());
        this.baseMapper.insert(articleCatg);
        return ResultObj.success();
    }

    @Override
    public ResultObj list(ArticleCatgQuery articleCatgQuery) {
        // MyBatisPlus 会改变原本的articleCatgQuery对象，所以这里深clone一个出来用，不信的话，你传递 articleCatgQuery 试试
        final ArticleCatgQuery query = ObjectUtil.clone(articleCatgQuery);
        return ResultObj.success(getChild(baseMapper.listByParentId(0, query), articleCatgQuery));
    }


    /**
     * 递归去获取子节点
     * @param list
     * @return
     */
    public IPage<ArticleCatgVO> getChild(IPage<ArticleCatgVO> list, ArticleCatgQuery query) {
        if (list.getRecords().size() == 0) {
            return null;
        }
        for (ArticleCatgVO item : list.getRecords()) {
            IPage<ArticleCatgVO> tmpList = baseMapper.listByParentId(item.getId(), query);

            item.setChildren(CollectionUtil.isEmpty(tmpList.getRecords()) ? null : tmpList.getRecords());
            getChild(tmpList, query);
        }
        return list;
    }

    @Override
    public ResultObj modify(ArticleCatg articleCatg, InformationVO informationVO) {
        articleCatg.setCreateBy(informationVO.getId())
                .setUpdateAt(LocalDateTime.now());
        this.baseMapper.updateById(articleCatg);
        return ResultObj.success();
    }
}
