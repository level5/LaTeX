/*
 * stack.c
 *
 *  Created on: 2015-6-28
 *      Author: user
 */

#include <stdlib.h>
#include "head/stack.h"
#include "head/list.h"

int stack_push(Stack *stack, const void *data)
{
    return list_ins_next(stack, NULL, data);
}

int stack_pop(Stack *stack, void **data)
{
    return list_rem_next(stack, NULL, data);
}


