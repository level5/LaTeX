################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/arguments_list.c \
../src/clist.c \
../src/define_macro.c \
../src/dlist.c \
../src/events.c \
../src/gcd.c \
../src/learning-c.c \
../src/list.c \
../src/queue.c \
../src/recursion.c \
../src/set.c \
../src/stack.c \
../src/variable_scope.c 

OBJS += \
./src/arguments_list.o \
./src/clist.o \
./src/define_macro.o \
./src/dlist.o \
./src/events.o \
./src/gcd.o \
./src/learning-c.o \
./src/list.o \
./src/queue.o \
./src/recursion.o \
./src/set.o \
./src/stack.o \
./src/variable_scope.o 

C_DEPS += \
./src/arguments_list.d \
./src/clist.d \
./src/define_macro.d \
./src/dlist.d \
./src/events.d \
./src/gcd.d \
./src/learning-c.d \
./src/list.d \
./src/queue.d \
./src/recursion.d \
./src/set.d \
./src/stack.d \
./src/variable_scope.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


