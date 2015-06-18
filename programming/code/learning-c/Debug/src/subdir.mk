################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/arguments_list.c \
../src/define-macro.c \
../src/learning-c.c \
../src/variable_scope.c 

OBJS += \
./src/arguments_list.o \
./src/define-macro.o \
./src/learning-c.o \
./src/variable_scope.o 

C_DEPS += \
./src/arguments_list.d \
./src/define-macro.d \
./src/learning-c.d \
./src/variable_scope.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


