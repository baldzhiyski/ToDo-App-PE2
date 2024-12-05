import { ref, type Ref } from "vue";
import { faInfo, faCheckCircle, faExclamationCircle, faTimesCircle, faBan } from "@fortawesome/free-solid-svg-icons";

type ToastType = "info" | "success" | "error" | "warning" | "dark";

export class AppToast {
    title: string;
    message: string;
    icon: any;
    type: ToastType;
    expired = false;
    key = Math.random();

    constructor({
                    title,
                    message,
                    type = "info",
                    icon = faInfo,
                    timeout = 5,
                }: {
        title: string;
        message: string;
        type?: ToastType;
        icon?: any;
        timeout?: number;
    }) {
        this.title = title;
        this.message = message;
        this.icon = icon;
        this.type = type;

        setTimeout(() => this.close(), timeout * 1_000);
    }

    close() {
        this.expired = true;
        activeToasts.value = activeToasts.value.filter((toast) => !toast.expired);
    }
}

export const activeToasts: Ref<AppToast[]> = ref([]);

export function showToast(toast: AppToast) {
    activeToasts.value.push(toast);
}

export function createToast({
                                title,
                                message,
                                type,
                                icon,
                                timeout,
                            }: {
    title: string;
    message: string;
    type?: ToastType;
    icon?: any;
    timeout?: number;
}) {
    const toast = new AppToast({ title, message, type, icon, timeout });
    showToast(toast);
}
