package com.ssafy.fitmu.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitmu.dto.ProductImage;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.Story;
import com.ssafy.fitmu.dto.StoryImage;
import com.ssafy.fitmu.service.StoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/story-api")
@Tag(name = "게시글 컨트롤러")
public class StoryController {
	private StoryService storyService;

	public StoryController(StoryService storyService) {
		this.storyService = storyService;
	}

	// 게시글 1개 조회
	@GetMapping("/{storyId}")
	public ResponseEntity<?> storyDetail(@PathVariable("storyId") int id) {
		Story story = storyService.selectOne(id);

		if (story == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Story>(story, HttpStatus.OK);
		}
	}

	// 게시글 등록
	@PostMapping("/regist")
	public ResponseEntity<?> storyRegist(@RequestBody Story story) {
		int result = storyService.insertStory(story);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 수정
	@PutMapping("/{storyId}")
	public ResponseEntity<?> storyUpdate(@PathVariable("storyId") int id, @RequestBody Story story) {
		story.setStoryId(id);

		int result = storyService.updateStory(story);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 삭제
	@DeleteMapping("/{storyId}")
	public ResponseEntity<?> storyDelete(@PathVariable("storyId") int id) {
		int result = storyService.deleteStory(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 전체 조회
	@GetMapping("/story")
	public ResponseEntity<?> storyAll() {
		List<Story> storyList = storyService.selectAll();

		if (storyList == null || storyList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
	}

	// 게시글 검색
	@GetMapping("/search")
	public ResponseEntity<?> search(@ModelAttribute SearchCondition condition) {
		List<Story> storyList = storyService.searchStory(condition);

		if (storyList == null || storyList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
	}

	// 해당 유저가 쓴 게시글
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> searchByUserId(@PathVariable("userId") int id) {
		List<Story> storyList = storyService.selectByUserId(id);

		if (storyList == null || storyList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
	}

	// 게시글 좋아요 증가
	@PutMapping("/like/{storyId}")
	public ResponseEntity<?> likeStory(@PathVariable("storyId") int id) {
		int result = storyService.increaseLikeCnt(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 좋아요 감소
	@PutMapping("/dislike/{storyId}")
	public ResponseEntity<?> disLikeStory(@PathVariable("storyId") int id) {
		int result = storyService.decreaseLikeCnt(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 스크랩북 등록
	@PostMapping("/story/{storyId}/user/{userId}")
	public ResponseEntity<?> registScrapBook(@PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
		int result = storyService.insertScrapBook(userId, storyId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 게시글 스크랩북 삭제
	@DeleteMapping("/story/{storyId}/user/{userId}")
	public ResponseEntity<?> removeScrapBook(@PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
		int result = storyService.deleteScrapBook(userId, storyId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@GetMapping("/story/popular")
	public ResponseEntity<?> popularStory() {
		List<Story> storyList = storyService.popularStory();

		if (storyList == null || storyList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/story/like")
	public ResponseEntity<?> likeStory() {
		List<Story> storyList = storyService.likeStory();

		if (storyList == null || storyList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadStoryImage(@RequestParam("file") MultipartFile file, @RequestBody StoryImage storyImage) {

		UUID uuid = UUID.randomUUID();
		String originalFilename = file.getOriginalFilename(); // 원본 파일 이름
		String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); // 파일 확장자 추출
		String newFilename = uuid.toString() + extension; // UUID를 파일 이름에 추가
		storyImage.setFileName(newFilename);
		
		try {
			int result = storyService.insertStoryImage(storyImage);
			// 파일 경로 재설정 필요!!!!!해요. (나도 재설성 해야함..)
			file.transferTo(new File("C:/testproject/SSAFIT-Project/src/assets/image/story",newFilename));
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(newFilename, HttpStatus.OK);
	}
	
	@GetMapping("/image/{storyId}")
	public ResponseEntity<?> getProductImageByStoryId(@PathVariable("storyId") int storyId){
		List<StoryImage> storyImageList = storyService.getImageByStoryId(storyId);
		
		if(storyImageList == null || storyImageList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<StoryImage>>(storyImageList, HttpStatus.OK);
		}
	}
}
