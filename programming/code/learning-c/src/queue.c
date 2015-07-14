/*
 * queue.c
 *
 *  Created on: 2015-6-28
 *      Author: user
 */

#include <stdlib.h>
#include "head/list.h"
#include "head/queue.h"

int queue_enqueue(Queue *queue, const void *data)
{
    return list_ins_next(queue, list_tail(queue), data);
}

int queue_dequeue(Queue *queue, void **data)
{
    return list_rem_next(queue, NULL, data);
}

