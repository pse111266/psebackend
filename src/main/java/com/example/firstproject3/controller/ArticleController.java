package com.example.firstproject3.controller;
import org.springframework.ui.Model;
import com.example.firstproject3.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject3.dto.ArticleForm; //ARticleForm 패키지 자동 임포트
import com.example.firstproject3.repository.ArticleRepository;
import java.text.AttributedString;
import java.util.ArrayList;

@Slf4j
@Controller //컨트롤러 선언
public class ArticleController {
    @Autowired // 리파지터리 객체 주입(의존성 주입)
    private ArticleRepository articleRepository; //-> 객체 선언
    @GetMapping("/articles/new") //URL 요청 접수

    public String newArticleForm(){
        return "articles/new";
        //메서드
    }

    @PostMapping("/articles/create")
    //데이터 보낸 곳
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article=form.toEntity(); // -> DTO 인 form 객체를 엔티티 객체로 변환
       //  System.out.println(article.toString()); //DTO -> 엔티티
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved= articleRepository.save(article); //articleRepository.save() 메서드를 호출해 article 엔티티를 저장
        // System.out.println(saved.toString()); //article -> DB에 저장되는지
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //PathVariable: URL 요청으로 들어온 전달값을 컨트롤러의 매개 변수로 가져옴
        log.info("id= "+id); //아이디 잘 받았는지 확인하는 로그

        // 1. id 를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity); //article이라는 이름으로 articleEntity 객체 등록
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles") //이 요청이 들어오면 index() 메서드 수행
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList=articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList); //articleEntityList 등록
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit") //컨트롤러에서 URL 변수 -> 중괄호 1개
    public String edit(@PathVariable Long id,Model model){

        //DB에서 수정할 데이터 가져오기
        Article articleEntity=articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기(articleEntitiy를 article로)
        model.addAttribute("article",articleEntity);

        return "articles/edit";
    }

}

