package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.Interview;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.InterviewService;

@RestController
public class InterviewController extends BaseController<Interview> {

    @Autowired
    private InterviewService interviewService;

    @Override
    BaseService<Interview> getService() {
        return null;
    }
}
