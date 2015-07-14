/*
 * clist.h
 *
 *  Created on: 2015-6-28
 *      Author: user
 */

#ifndef CLIST_H_
#define CLIST_H_

#include <stdlib.h>

typedef struct CListElmet_
{
    void *data;
    struct CListElmet_ *next;
} CListElmt;

typedef struct CList_
{
    int size;
    int (*match)(const void *key1, const void *key2);
    void (*destroy)(void *data);
    CListElmt *head;
} CList;

void clist_init(CList *list, void (*destroy)(void *data));
void clist_destroy(CList *list);
int clist_ins_next(CList *list, CListElmt *element, const void *data);
int clist_rem_next(CList *list, CListElmt *element, void **data);

#define clist_size(list) ((list)->size)
#define clist_head(list) ((list)->head)
#define clist_data(element) ((element)->data)
#define clist_next(element) ((element)->next)

#endif /* CLIST_H_ */
