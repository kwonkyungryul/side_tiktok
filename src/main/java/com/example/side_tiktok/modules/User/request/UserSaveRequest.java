package com.example.side_tiktok.modules.User.request;

import jakarta.validation.constraints.NotBlank;

public record UserSaveRequest(
        @NotBlank(message = "유저 이름을 입력해주세요.")
        String username,
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,
        @NotBlank(message = "이메일을 입력해주세요.")
        String email,
        @NotBlank(message = "생년월일을 입력해주세요.")
        String birth
) {
}
