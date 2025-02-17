<template>
  <v-list-item
    :aria-label="task.inFinishProcess ? 'Aufgabe wird gerade abgeschlossen' :'Aufgabe '+ task.name+ ' öffnen'"
    class="d-flex align-center"
    :style="task.inFinishProcess && 'background-color: #F8F8F8; border-radius:6px; cursor: not-allowed; color: #AAA'"
    :to="!task.inFinishProcess ? {path: '/task/'+task.id } : ''"
  >
    <v-flex
      class="d-flex flex-column taskColumn"
      style="min-height: 4.5rem; max-height: 6.5rem; margin: 8px 0"
    >
      <h2 class="taskTitle">
        <text-highlight :queries="searchString">
          {{ task.name }}
        </text-highlight>
        <v-chip
          v-if="task.tag"
          small
          @click.prevent="$emit('clickTag', task.tag)"
        >
          {{ task.tag }}
        </v-chip>
      </h2>
      <p
        v-if="task.inFinishProcess"
        class="grey--text"
        style="font-size: 0.9rem"
      >
        <v-icon>mdi-progress-clock</v-icon>
        Task wird aktuell abgeschlossen
      </p>
      <p
        v-if="task.followUpDateFormatted"
        class="grey--text"
        style="font-size: 0.9rem"
      >
        Wiedervorlage am {{ task.followUpDateFormatted }}
      </p>
      <p>
        <text-highlight :queries="searchString">
          {{ task.description }}
        </text-highlight>
      </p>
    </v-flex>
    <v-flex
      style="min-width: 200px; max-width: 200px"
      class="taskColumn"
    >
      <p class="taskInfo">
        <text-highlight :queries="searchString">
          {{ task.processName }}
        </text-highlight>
      </p>
    </v-flex>
    <v-flex
      style="min-width: 80px; max-width: 80px"
      class="taskColumn"
    >
      <p class="taskInfo">
        {{ task.createTime }}
      </p>
    </v-flex>
    <v-flex
      style="min-width: 25px; max-width: 25px"
      class="d-flex justify-end align-center ml-2"
    >
      <v-menu
        v-if="!task.inFinishProcess"
        top
        offset-x
      >
        <template #activator="{ on, attrs }">
          <v-btn
            icon
            v-bind="attrs"
            @click="(event) => { event.preventDefault()}"
            v-on.prevent="on"
            aria-label="Aktionen für die Aufgabe"
            aria-hidden="false"
          >
            <v-icon aria-label="Aktionen für die Aufgabe" role="img" aria-hidden="false">mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            :aria-label="'Aufgabe '+task.name+ ' öffnen'"
            link
            :to="{ path: '/task/'+task.id }"
            @click="(event) => { event.preventDefault()}"
          >
            <div>Öffnen</div>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-flex>
  </v-list-item>
</template>

<style scoped>

.taskColumn {
  margin: 0 0 0 8px;
  align-self: baseline;
  overflow: hidden;
}

.taskTitle {
  font-size: 1.1rem;
  font-weight: 600;
  display: flex;
  justify-content: space-between;
}

.taskInfo {
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
}

.taskInfo span {
  margin-right: 0.5rem;
}

</style>

<script lang="ts">
import {HumanTask} from "../../middleware/tasks/tasksModels";
import {PropType} from "vue";
import {useGetPaginationData} from "../../middleware/paginationData";

export default {
  props: {
    task: {
      type: Object as PropType<HumanTask>, // HumanTask
      required: true
    },
    searchString: {
      type: String,
      default: ""
    }
  },
  emits: {
    clickTag: {
      type: Function as PropType<(tag: string) => void>
    },
  },
};

</script>
