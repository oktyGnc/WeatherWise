package com.oktaygenc.weatherwise.domain.usecase

interface BaseUseCase<in Params, out Type> {
    suspend operator fun invoke(params: Params): Type
}
interface BaseUseCaseWithoutParams<out Type> {
    suspend operator fun invoke(): Type
} 