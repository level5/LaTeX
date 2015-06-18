################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/daemon_error.c \
../src/error.c \
../src/file_io.c \
../src/process_environment.c \
../src/statckoverflow_question.c \
../src/unix-programming.c 

OBJS += \
./src/daemon_error.o \
./src/error.o \
./src/file_io.o \
./src/process_environment.o \
./src/statckoverflow_question.o \
./src/unix-programming.o 

C_DEPS += \
./src/daemon_error.d \
./src/error.d \
./src/file_io.d \
./src/process_environment.d \
./src/statckoverflow_question.d \
./src/unix-programming.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


