package com.judgify.api.service;

import com.judgify.core.problem.dto.TestCaseCreateRequest;
import com.judgify.core.problem.dto.TestCaseResponse;
import com.judgify.core.problem.dto.TestCaseUpdateRequest;
import com.judgify.core.problem.entity.Problem;
import com.judgify.core.problem.entity.TestCase;
import com.judgify.core.problem.exception.ProblemNotFoundException;
import com.judgify.core.problem.exception.TestCaseNotFoundException;
import com.judgify.core.problem.repository.ProblemRepository;
import com.judgify.core.problem.repository.TestCaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final ProblemRepository problemRepository;

    public TestCaseService(TestCaseRepository testCaseRepository, ProblemRepository problemRepository) {
        this.testCaseRepository = testCaseRepository;
        this.problemRepository = problemRepository;
    }

    @Transactional(readOnly = true)
    public List<TestCaseResponse> getTestCasesByProblemId(Long problemId) {
        if (!problemRepository.existsById(problemId)) {
            throw new ProblemNotFoundException(problemId);
        }
        return testCaseRepository.findByProblemIdOrderByOrderIndexAsc(problemId)
                .stream()
                .map(TestCaseResponse::from)
                .toList();
    }

    @Transactional
    public TestCaseResponse createTestCase(Long problemId, TestCaseCreateRequest request) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ProblemNotFoundException(problemId));

        TestCase testCase = TestCase.builder()
                .problem(problem)
                .input(request.input())
                .expectedOutput(request.expectedOutput())
                .sample(request.sample())
                .orderIndex(request.orderIndex())
                .build();

        TestCase saved = testCaseRepository.save(testCase);
        return TestCaseResponse.from(saved);
    }

    @Transactional
    public TestCaseResponse updateTestCase(Long id, TestCaseUpdateRequest request) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new TestCaseNotFoundException(id));

        if (request.input() != null) {
            testCase.setInput(request.input());
        }
        if (request.expectedOutput() != null) {
            testCase.setExpectedOutput(request.expectedOutput());
        }
        if (request.sample() != null) {
            testCase.setSample(request.sample());
        }
        if (request.orderIndex() != null) {
            testCase.setOrderIndex(request.orderIndex());
        }

        TestCase saved = testCaseRepository.save(testCase);
        return TestCaseResponse.from(saved);
    }

    @Transactional
    public void deleteTestCase(Long id) {
        if (!testCaseRepository.existsById(id)) {
            throw new TestCaseNotFoundException(id);
        }
        testCaseRepository.deleteById(id);
    }
}

