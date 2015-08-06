/*
 * events.h
 *
 *  Created on: 2015-6-28
 *      Author: user
 */

#ifndef EVENTS_H_
#define EVENTS_H_

#include "queue.h"

typedef struct Event_
{
    int code;
} Event;

int receive_event(Queue *queue, const Event *event);
int process_event(Queue *queue, int (*dispatch)(Event *event));

#endif /* EVENTS_H_ */
