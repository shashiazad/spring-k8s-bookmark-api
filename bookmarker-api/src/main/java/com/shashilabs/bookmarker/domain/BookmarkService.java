package com.shashilabs.bookmarker.domain;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository repository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional
    public BookmarksDTO getBookmark(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = repository.findBookmarks(pageable);
        return new BookmarksDTO(bookmarkPage);
    }

    @Transactional
    public BookmarksDTO searchBookmark(Integer page, String query) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        //Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);
        Page<BookmarkDTO> bookmarkPage = repository.findByTitleContainsIgnoreCase(query, pageable);

        return new BookmarksDTO(bookmarkPage);
    }


    public BookmarkDTO createBookmark(@Valid CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
        Bookmark savedBookmark = repository.save(bookmark);
        return  bookmarkMapper.toDTO(savedBookmark);
    }
}

