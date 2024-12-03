<template>
  <div class="main-container">
    <!-- Header and Add Assignee Button -->
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2><i class="fas fa-users"></i> Assignees</h2>
      <button @click="openCreateModal" class="btn btn-primary m-lg-1">
        <i class="fas fa-plus-circle"></i> Add Assignee
      </button>
    </div>

    <!-- Assignees List -->
    <!-- Assignees List -->
    <div class="row g-4" v-if="assignees.length">
      <div class="col-md-3" v-for="assignee in assignees" :key="assignee.id">
        <div class="card shadow-lg border-0 m-lg-1">
          <div class="card-body">
            <h5 class="card-title text-primary">
              <i class="fas fa-id-badge"></i> {{ assignee.prename }} {{ assignee.name }}
            </h5>
            <p class="card-text mb-1">
              <i class="fas fa-envelope"></i> {{ assignee.email }}
            </p>
            <p class="card-text">
              <i class="fas fa-hashtag"></i> ID: {{ assignee.id }}
            </p>

            <div class="d-flex justify-content-between mt-3 flex-wrap">
              <!-- Edit icon button -->
              <button class="btn btn-link p-0" @click="openEditModal(assignee)"
                      style="font-size: 24px; color: #6c757d; border: none; background: transparent; cursor: pointer;">
                <i class="fas fa-edit"></i>
              </button>

              <!-- Info icon button -->
              <button class="btn btn-link p-0" @click="openDetails(assignee)"
                      style="font-size: 24px; color: #17a2b8; border: none; background: transparent; cursor: pointer;">
                <i class="fas fa-info-circle"></i>
              </button>

              <!-- Trash icon button -->
              <button class="btn btn-link p-0" @click="deleteAssignee(assignee.id)"
                      style="font-size: 24px; color: #dc3545; border: none; background: transparent; cursor: pointer;">
                <i class="fas fa-trash-alt"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Error Message -->
    <div v-else class="alert alert-danger text-center">
      <i class="fas fa-database"></i> Failed to load assignees. Please check your database connection and try again.
    </div>



    <!-- Assignee Details Modal -->
    <ModalComponent v-model:isVisible="isDetailsModalVisible" :assignee="selectedAssignee">
      <div class="modal-content">
        <h3 class="modal-header text-center">
          <i class="fas fa-info-circle"></i> Assignee Details
        </h3>
        <div class="modal-body">
          <p><strong>Pre-name:</strong> {{ selectedAssignee?.prename }}</p>
          <p><strong>Name:</strong> {{ selectedAssignee?.name }}</p>
          <p><strong>Email:</strong> {{ selectedAssignee?.email }}</p>
          <p><strong>ID:</strong> {{ selectedAssignee?.id }}</p>
          <hr>
          <h4>Assigned To-Do</h4>
          <div v-if="selectedAssignee?.toDoDetails">
            <h5>{{ selectedAssignee.toDoDetails.title }}</h5>
            <p><strong>Status:</strong> <span :class="{'text-success': selectedAssignee.toDoDetails.finished, 'text-danger': !selectedAssignee.toDoDetails.finished}">
              {{ selectedAssignee.toDoDetails.finished ? 'Completed' : 'Pending' }}
            </span></p>
            <p><strong>Due Date:</strong> {{ formatDate(selectedAssignee.toDoDetails.dueDate) }}</p>
            <p v-if="selectedAssignee.toDoDetails.finishedDate"><strong>Finished Date:</strong> {{ formatDate(selectedAssignee.toDoDetails.finishedDate) }}</p>
            <hr>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" @click="closeDetailsModal">
            <i class="fas fa-times"></i> Close
          </button>
        </div>
      </div>
    </ModalComponent>

    <!-- Create Assignee Modal -->
    <ModalComponent v-model:isVisible="isCreateModalVisible">
      <h3><i class="fas fa-user-plus"></i> Create New Assignee</h3>
      <form @submit.prevent="createAssignee">
        <div class="mb-3">
          <label for="prename" class="form-label">Prename</label>
          <input type="text" v-model="newAssignee.prename" class="form-control" id="prename" required />
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">Name</label>
          <input type="text" v-model="newAssignee.name" class="form-control" id="name" required />
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" v-model="newAssignee.email" class="form-control" id="email" required />
        </div>
        <div class="d-flex justify-content-end">
          <button type="submit" class="btn btn-primary">
            <i class="fas fa-save"></i> Save
          </button>
        </div>
      </form>
    </ModalComponent>

    <!-- Edit Assignee Modal -->
    <ModalComponent v-model:isVisible="isEditModalVisible">
      <h3><i class="fas fa-user-edit"></i> Edit Assignee</h3>
      <form @submit.prevent="updateAssignee">
        <div class="mb-3">
          <label for="edit-prename" class="form-label">Prename</label>
          <input type="text" v-model="editAssignee.prename" class="form-control" id="edit-prename"  />
        </div>
        <div class="mb-3">
          <label for="edit-name" class="form-label">Name</label>
          <input type="text" v-model="editAssignee.name" class="form-control" id="edit-name"  />
        </div>
        <div class="mb-3">
          <label for="edit-email" class="form-label">Email</label>
          <input type="email" v-model="editAssignee.email" class="form-control" id="edit-email"  />
        </div>
        <div class="d-flex justify-content-end">
          <button type="submit" class="btn btn-primary">
            <i class="fas fa-save"></i> Save
          </button>
        </div>
      </form>
    </ModalComponent>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import ModalComponent from "../components/ModalComponent.vue";
import apiClient from "../lib/apiClient.ts";
import {createToast, AppToast} from "../ts/toasts";
import {faCheck, faXmark} from "@fortawesome/free-solid-svg-icons";


export default {
  components: {
    ModalComponent
  },
  setup() {
    const isDetailsModalVisible = ref(false);
    const assignees = ref([]);
    const isCreateModalVisible = ref(false);
    const isEditModalVisible = ref(false);
    const newAssignee = ref({ prename: '', name: '', email: '' });
    const editAssignee = ref({ id: null, prename: '', name: '', email: '' });
    const selectedAssignee = ref(null);

    // Fetch all assignees from API
    const fetchAssignees = async () => {
      try {
        const response = await apiClient.get('assignees');
        assignees.value = response;
      } catch (error) {
        console
        createToast( {
          title: "Validation Errors",  // Toast title
          message: "Failed to fetch",  // The concatenated error messages,
          icon: faXmark,
          type: "error",  // Toast type (error)
          timeout: 5,  // Duration for the toast in seconds
        })
      }
    };

    const createAssignee = async () => {
      try {
        await apiClient.post('assignees', newAssignee.value);
        fetchAssignees();  // Reload assignees list
        isCreateModalVisible.value = false;  // Close modal
      } catch (error) {
        isCreateModalVisible.value = false;  // Close modal
        showValidationErrors(error.fieldErrors)
      }
    };

    const showValidationErrors = (errors) => {

      console.log(errors)
      // Convert the error object into an array of error messages
      const errorMessages = Object.entries(errors)  // Convert object to an array of [key, value] pairs
          .map(([field,message]) => `${message}`)  // Format each error message
          .join('<br/>');  // Join the messages with <br/> to create line breaks

      // Create the toast with the combined error messages
      createToast({
        title: "Validation Errors",  // Toast title
        message: errorMessages,  // The concatenated error messages,
        icon: faXmark,
        type: "error",  // Toast type (error)
        timeout: 5,  // Duration for the toast in seconds
      });
    };

    const updateAssignee = async () => {
      try {
        await apiClient.put(`assignees/${editAssignee.value.id}`, editAssignee.value);
        fetchAssignees();  // Reload assignees list
        isEditModalVisible.value = false;  // Close modal
      } catch (error) {
        isEditModalVisible.value = false;  // Close modal
        console.log(error)
        createToast({
          title: "Email already in use !",
          message: error,
          type: "error",
          icon: faXmark
        })
      }
    };



    const deleteAssignee = async (id) => {
      try {
        await apiClient.delete(`assignees/${id}`);
        fetchAssignees();  // Reload assignees list after deletion
      } catch (error) {
        createToast({
          title: "Error deleting assignee",
          message: error.message,
          type: "error",
          icon: faXmark
        })
        console.error('Error deleting assignee:', error);
      }
    };

    const fetchAssigneeDetails = async (id) => {
      try {
        return apiClient.get(`assignees/${id}`);
      } catch (error) {
        createToast("Error",error.message,faXmark, 10)
        console.error('Error fetching assignee details:', error);
      }
    };

    const openCreateModal = () => {
      isCreateModalVisible.value = true;
      newAssignee.value = { prename: '', name: '', email: '' };
    };

    const openEditModal = (assignee) => {
      editAssignee.value = { ...assignee };
      isEditModalVisible.value = true;
    };

    const openDetails = async (assignee) => {
      selectedAssignee.value = await fetchAssigneeDetails(assignee.id);
      isDetailsModalVisible.value = true;
    };

    const closeDetailsModal = () => {
      isDetailsModalVisible.value = false;
      selectedAssignee.value = null;
    };

    const formatDate = (date) => {
      return new Date(date).toLocaleDateString();
    };

    onMounted(fetchAssignees);

    return {
      assignees,
      fetchAssignees,
      createAssignee,
      updateAssignee,
      deleteAssignee,
      showValidationErrors,
      isCreateModalVisible,
      isEditModalVisible,
      isDetailsModalVisible,
      newAssignee,
      editAssignee,
      selectedAssignee,
      openCreateModal,
      openEditModal,
      openDetails,
      closeDetailsModal,
      formatDate
    };
  }
};
</script>




<style scoped>
/* Font Awesome and Bootstrap Styling */

.main-container {
  overflow-x: hidden; /* Prevent horizontal scroll on the main container */
}
/* Card Styling */
.card {
  border-radius: 12px; /* Rounded corners for a softer look */
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Soft shadow for subtle depth */
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  background-color: #f8f9fa; /* Light Grey background */
}

.card:hover {
  transform: translateY(-5px); /* Slight lift effect */
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15); /* Deeper shadow on hover */
}

.card-title {
  font-size: 1.15rem;
  font-weight: bold;
  color: #333; /* Darker text for better readability */
}

.card-text {
  color: #6c757d; /* Light grey text for secondary info */
}

/* Button Styling */
button {
  min-width: 100px;
  transition: all 0.3s ease;
}

button:hover {
  transform: translateY(-2px); /* Subtle lift effect */
}

/* Primary Button */
button.btn-primary {
  background: linear-gradient(135deg, #4e73df, #1c3d9d); /* Blue gradient */
  border-color: #4e73df;
  color: white;
}

button.btn-primary:hover {
  background: linear-gradient(135deg, #3b5ac9, #193e8a); /* Darker blue on hover */
}

/* Secondary Button */
button.btn-secondary {
  background: linear-gradient(135deg, #ff9b91, #ff6a57); /* Warm pink to coral gradient */
  border-color: #ff9b91;
  color: white;
}

button.btn-secondary:hover {
  background: linear-gradient(135deg, #ff6a57, #ff4b39); /* Darker pink-orange on hover */
}

/* Info Button */
button.btn-info {
  background: linear-gradient(135deg, #17a2b8, #0b7984); /* Teal to blue gradient */
  border-color: #17a2b8;
  color: white;
}

button.btn-info:hover {
  background: linear-gradient(135deg, #0b7984, #007682); /* Darker teal on hover */
}

/* Icon Buttons */
button.btn-link {
  background: transparent;
  padding: 0;
  font-size: 24px;
  color: inherit; /* Use text color for icons */
  border: none;
  cursor: pointer;
}

button.btn-link:hover {
  color: #007bff; /* Change icon color on hover to indicate it's clickable */
  transform: scale(1.1); /* Slightly enlarge icon on hover */
}


</style>
