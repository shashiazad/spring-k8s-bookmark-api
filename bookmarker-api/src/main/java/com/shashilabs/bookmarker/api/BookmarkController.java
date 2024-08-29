package com.shashilabs.bookmarker.api;

import com.shashilabs.bookmarker.domain.BookmarkDTO;
import com.shashilabs.bookmarker.domain.BookmarkService;
import com.shashilabs.bookmarker.domain.BookmarksDTO;
import com.shashilabs.bookmarker.domain.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query) {
        if(query == null || query.trim().isEmpty()){
            return  bookmarkService.getBookmark(page);
        }
        return bookmarkService.searchBookmark(page, query);
    }

    @PostMapping
    public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request){
        return bookmarkService.createBookmark(request);
    }
}
