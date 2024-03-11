package com.example.tweetService.dao.impl;

import com.example.tweetService.dao.ViewDao;
import com.example.tweetService.entity.View;
import com.example.tweetService.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewDaoImpl implements ViewDao {
    @Autowired
    private ViewRepository viewRepository;
    @Override
    public View findByTweetIdAndProfileId(Long tweetId, String profileId)
    {
        if(viewRepository.findByParentTweetIdAndProfileId(tweetId,profileId).isPresent())
            return viewRepository.findByParentTweetIdAndProfileId(tweetId,profileId).get();
        else
            return null;
    }
    @Override
    public View createView(View view)
    {
        viewRepository.saveAndFlush(view);
        return view;
    }
}
