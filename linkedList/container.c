#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

#include "container.h"
#include "room.h"
#include "item.h"
#include "command.h"

struct container* create_container(struct container* first, enum container_type type, void* entry) {
    if(entry==NULL) {
        return NULL;
    }
     struct container* krabica = calloc( (size_t) 1, sizeof(struct container));
    if(krabica == NULL) { 
        return NULL;
    }

    krabica->type = type;

    if(krabica->type == ITEM) {
        krabica->item = entry;
    }
    else if(krabica->type == ROOM) {
        krabica->room = entry;
    }
    else if(krabica->type == COMMAND) {
        krabica->command = entry;
    }
    else {
       krabica->text=entry;
    }

    krabica->next = NULL;

    struct container* pomocna = first;
    if(pomocna==NULL){
        first = krabica;
        return first;
    }
    while(pomocna->next != NULL) {
        pomocna = pomocna->next;
    }
    pomocna->next = krabica;

    return first;
}

struct container* destroy_containers(struct container* first) {
    if(first == NULL) {
        return NULL;
    }    

    if(first->next != NULL) {
        destroy_containers(first->next);
    }

    if(first->type == ITEM) {
        destroy_item(first->item);
    }
    else if(first->type == ROOM) {
        destroy_room(first->room);
    }
    else if(first->type == COMMAND) {
        destroy_command(first->command);
    }
    else {
        free(first->text);
    }

    free(first);
    return NULL;
}

void* get_from_container_by_name(struct container *first, const char *name) {
    if(first == NULL || name == NULL) {
        return NULL;
    }

    if(strcmp(name, "") == 0) {
        return NULL;
    }

    struct container* pomocna = first;
    while(pomocna != NULL) {
        if(pomocna->type == ITEM) {
            if (pomocna->item==NULL){
                return NULL;
            }
            if(strcasecmp(pomocna->item->name, name) == 0) {
                return pomocna->item;
            }
        } else if(pomocna->type == ROOM) {
            if (pomocna->room==NULL){
                return NULL;
            }
            if(strcasecmp(pomocna->room->name, name) == 0) {
                return pomocna->room;
            }
        } else if(pomocna->type == COMMAND) {
            if (pomocna->command==NULL){
                return NULL;
            }
            if(strcasecmp(pomocna->command->name, name) == 0) {
                return pomocna->command;
            }
        } else {
            if (pomocna->text==NULL){
                return NULL;
            }
            if(strcasecmp(pomocna->text, name) == 0) {
                return pomocna->text;
            }
        }
        pomocna = pomocna->next;
        if(pomocna==NULL) {
            return NULL;
        } 
    }

    return NULL;
}

struct container* remove_container(struct container *first, void *entry) {
    if(first == NULL || entry == NULL) {
        return NULL;
    }
    
    struct container* predchadzajuci = NULL;
    struct container* tento = first;
    struct container* dalsi = first->next;

    while(tento != NULL) {
        struct item* pomocny_item;
        struct room* pomocny_room;
        struct command* pomocny_command;

        if(tento->type == ROOM) {
            pomocny_room = entry;
            if(strcasecmp(tento->room->name, pomocny_room->name) == 0) {
                if(predchadzajuci == NULL) {
                    free(tento);
                    return dalsi;
                } else {
                    predchadzajuci->next = dalsi;
                    free(tento);
                    return first;
                }
            }
        } else if(tento->type == ITEM) {
            pomocny_item = entry;
            if(strcasecmp(tento->item->name, pomocny_item->name) == 0) {
                if(predchadzajuci == NULL) {
                    free(tento);
                    return dalsi;
                } else {
                    predchadzajuci->next = dalsi;
                    free(tento);
                    return first;
                }
            }
        } else if(tento->type == COMMAND) {
            pomocny_command = entry;
            if(strcasecmp(tento->command->name, pomocny_command->name) == 0) {
                if(predchadzajuci == NULL) {
                    free(tento);
                    return dalsi;
                } else {
                    predchadzajuci->next = dalsi;
                    free(tento);
                    return first;
                }
            }
        } else if(tento->type == TEXT) {
            if(strcasecmp(tento->text, entry) == 0) {
                if(predchadzajuci == NULL) {
                    free(tento);
                    return dalsi;
                } else {
                    predchadzajuci->next = dalsi;
                    free(tento);
                    return first;
                }
            }
        }

        predchadzajuci = tento;
        tento = dalsi;
        if(tento == NULL){
            return first;
        }
        dalsi = dalsi->next;
    }

    return first;
}
