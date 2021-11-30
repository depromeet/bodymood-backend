package com.depromeet.dgdg.service.user

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.user.dto.request.UpdateUserInfoRequest
import com.depromeet.dgdg.utils.setUpUser
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceTest(
    private val userService: UserService,
    private val userRepository: UserRepository,
) : FunSpec({

    afterEach {
        userRepository.deleteAll()
    }

    context("나의 회원정보 수정") {
        test("나의 회원 정보르 수정하면 해당하는 유저의 데이터가 변경된다") {
            // given
            val user = userRepository.save(setUpUser())

            val request = UpdateUserInfoRequest("will", "https://will.profile.com")

            // when
            userService.updateUserInfo(request, user.id)

            // then
            val users = userRepository.findAll()
            users shouldHaveSize 1
            users[0].also {
                it.name shouldBe request.name
                it.profileUrl shouldBe request.profileUrl
                it.socialId shouldBe user.socialId
                it.socialProvider shouldBe user.socialProvider
            }
        }

        test("존재하지 않는 유저인 경우 회원 정보를 수정할 수 없다 throws NotFoundException") {
            // given
            val userId = -1L
            val request = UpdateUserInfoRequest("will", "https://will.profile.com")

            // when & then
            shouldThrowExactly<NotFoundException> {
                userService.updateUserInfo(request, userId)
            }
        }
    }

})
