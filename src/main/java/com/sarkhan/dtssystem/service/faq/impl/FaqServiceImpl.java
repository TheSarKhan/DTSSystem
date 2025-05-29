package com.sarkhan.dtssystem.service.faq.impl;

import com.sarkhan.dtssystem.dto.faq.FaqDTO;
import com.sarkhan.dtssystem.model.content.faq.FAQ;
import com.sarkhan.dtssystem.repository.content.faq.FAQRepository;
import com.sarkhan.dtssystem.service.faq.service.FaqService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FAQRepository faqRepository;

    @Override
    public FAQ create(FaqDTO dto) {
        FAQ faq = new FAQ();
        faq.setQuestion(dto.getQuestion());
        faq.setAnswer(dto.getAnswer());

        return faqRepository.save(faq);
    }

    @Override
    public FAQ getById(Long id) {

        return faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ not found"));
    }

    @Override
    public List<FAQ> getAll() {
        return faqRepository.findAll();
    }

    @Override
    public FAQ update(Long id, FaqDTO dto) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ not found"));
        faq.setAnswer(dto.getAnswer());
        faq.setQuestion(dto.getQuestion());

        return faqRepository.save(faq);
    }

    @Override
    public void delete(Long id) {
        faqRepository.deleteById(id);
    }
}
