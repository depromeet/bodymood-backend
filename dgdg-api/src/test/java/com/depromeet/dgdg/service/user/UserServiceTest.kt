package com.depromeet.dgdg.service.user

import com.depromeet.dgdg.common.exception.NotFoundException
import com.depromeet.dgdg.domain.domain.poster.repository.PosterExerciseCategoryRepository
import com.depromeet.dgdg.domain.domain.user.SocialProvider
import com.depromeet.dgdg.domain.domain.user.UserCreator
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository
import com.depromeet.dgdg.service.user.dto.request.UserRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceTest(
    private val userService: UserService,
    private val userRepository: UserRepository,
//    private val posterExerciseCategoryRepository: PosterExerciseCategoryRepository
) : FunSpec({

    afterEach {
        userRepository.deleteAll()
//        posterExerciseCategoryRepository.deleteAll()
    }

    val user = UserCreator.create(
        socialId = "social-id",
        socialProvider = SocialProvider.KAKAO,
        name = "득근득근",
        profileUrl = "https://dgdg.profile.com"
    )

    context("유저의 회원정보 수정") {
        test("회원정보 수정을 요청하면 DB의 해당하는 유저의 회원 정보가 변경된다") {
            // given
            userRepository.save(user)

            val request = UserRequest("will", "https://will.profile.com")

            // when
            userService.updateUserInfo(request, user.id)

            // then
            val users = userRepository.findAll()
            users shouldHaveSize 1
            users[0].name shouldBe request.name
            users[0].profileUrl shouldBe request.profileUrl
            users[0].socialId shouldBe user.socialId
            users[0].socialProvider shouldBe user.socialProvider
        }

        test("존재하지 않는 유저인 경우 NotFound 에러 발생") {
            // given
            val userId = 999L
            val request = UserRequest("will", "https://will.profile.com")

            // when & then
            assertThatThrownBy {
                userService.updateUserInfo(
                    request,
                    userId
                )
            }.isInstanceOf(NotFoundException::class.java)
        }
    }

})
