package com.month.spent.dto.user.authentication;

public record ChangePasswordDTO(
        String oldPassword,
        String newPassword
) {
}
