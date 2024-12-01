<template>
  <div v-if="isVisible" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <header class="modal-header">
        <button @click="closeModal" class="close-button">
          <i class="fas fa-times"></i> <!-- Font Awesome Icon -->
        </button>
      </header>
      <div class="modal-body">
        <slot></slot>
      </div>
      <footer class="modal-footer">
        <button v-if="footerActions" @click="footerActions.onConfirm" class="btn btn-primary">
          <i class="fas fa-check-circle"></i> Confirm <!-- Font Awesome Icon -->
        </button>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isVisible: {
      type: Boolean,
      default: false
    },
    footerActions: {
      type: Object,
      default: () => null
    }
  },
  methods: {
    closeModal() {
      this.$emit('update:isVisible', false);
    }
  }
};
</script>

<style scoped>
/* Modal Overlay */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8); /* Darker overlay */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  animation: fadeIn 0.4s ease-in-out;
  backdrop-filter: blur(5px); /* Add blur effect */
}

/* Modal Content */
.modal-content {
  background: #fff;
  padding: 30px;
  width: 90%;
  max-width: 600px;
  border-radius: 15px; /* Rounded corners */
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
  animation: scaleUp 0.4s ease-in-out;
  transition: transform 0.3s ease, opacity 0.3s ease;
}

/* Modal Header */
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.modal-header h3 {
  font-size: 1.8rem;
  font-weight: bold;
  color: #333;
  margin: 0;
}

.close-button {
  background: none;
  border: none;
  font-size: 1.8rem;
  cursor: pointer;
  color: #333;
  transition: color 0.3s ease;
}

.close-button:hover {
  color: #e74c3c; /* Change color to red on hover */
}

/* Modal Body */
.modal-body {
  padding: 20px 0;
  font-size: 1rem;
  color: #666; /* Lighter text for content */
  line-height: 1.6;
}

/* Modal Footer */
.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 15px;
}

/* Button Styles */
.btn {
  padding: 12px 20px;
  font-size: 1rem;
  font-weight: 600;
  border-radius: 25px; /* Rounded buttons */
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 10px;
}

.btn-secondary {
  background-color: #f0f0f0;
  color: #555;
  border: 1px solid #ccc;
}

.btn-secondary:hover {
  background-color: #e0e0e0;
  border-color: #bbb;
  transform: scale(1.05);
}

.btn-primary {
  background: linear-gradient(135deg, #007bff, #0056b3); /* Gradient blue */
  color: white;
  border: 1px solid #007bff;
}

.btn-primary:hover {
  background: linear-gradient(135deg, #0056b3, #003f75);
  transform: scale(1.05);
}

/* Hover effects */
button:hover {
  opacity: 0.9;
}

/* Keyframe Animations */
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes scaleUp {
  from {
    transform: scale(0.9);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

/* Responsiveness */
@media (max-width: 768px) {
  .modal-content {
    width: 95%;
    padding: 20px;
  }

  .modal-header h3 {
    font-size: 1.4rem;
  }

  .btn {
    font-size: 0.9rem;
    padding: 10px 18px;
  }
}
</style>
